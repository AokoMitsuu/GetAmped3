	� G /efx/stageitem/core_red.mot��       �U�n�F][���P6d��%�vAx!ۊl'��	�#F�4_�d����t�M�e�-�E��?��Ro�-
��43��s���
��0׆ĳrN�`5��n�ь��J�B�,e\���Q+$�X/�����K)]�t蔏������beG7���8A;�:��ݽ�mקW��;�~�azR�a0���#��"CZG���K�x�Ņ�km5���)�je��p��w~��ñ�U���i�X��u�M`��4�>a~Q�E��}�&��x�EPLN#�Ec+����OS��T�4	E�,�Cl�Sr������O��/H���)�����d\�(��0��r�A�2BK����M�S�nȣ��t!c��]�9�V揿��?�!ǟ��?	����8Ɛ��4	�Kt������w��Vp��4q����6�	�B��QDsa\j��$ˀ0]�g����"��x	�뜍a�k�E�>��mF�6K��IL��x�;�c~�XG�?3k1�I \3�۞?�?�Z�O�̟�̟~�?�>�Ax��t��3����#��3�(e�c�f�w�р�P�����ug����ꅄ��>���0[ C�n�[�z爐�7�]�m�{lyΆ-�8ީ����-"_����ٖZWwv���6x�<o���S�)�l�FP�m��� ��A�X����~8<6|-��.��Vt�D5�M�bM�f�(s��f���:S<+;$1�8�
�򺺜E�S7Ŧ�UMh�w�#�$�Y$j	�r��O���C���L�D
h+H<x;�{�4
��՛!؃0뵋���0���q�h�ߓh�Vf��o�|TR�b�e�8#�-VA/�ڴHi������%�ɨ�9.-I�(��H���c�;V��l�*_�3���!��K�Zig�X���Eǲ�y�M�������YC�`k0EU�`"���f=A��Ѡ(�~K]������)�u���w~�d�c�F��s�g#`���JR�.�}����|HW^!�M[�?��H
   t  Lga2/stage/AmpedScript; e Lkotori/data/MixtureData; mix�� kotori.data.MixtureData� /efx/mix/bar7.agi���� mix2��� /efx/mix/bar4.agi���� Lkotori/data/KGLGenerationData; ripD /efx/ripple/ripple05_blu.ptc�            ?�  ?�  ?�               baseD  efx/stageitem/core/core_base.scm�            ?�  ?�  ?�                flashD */efx/stageitem/trap/item_box/ib_flash1.scm�        �333?�  ?�  ?�               start appear 	damagehit destroy revival exp setShadowState
 d y 3 getPosition ()Lkotori/geom/Position;
 f { | l pos (Lkotori/geom/Vector3D;)V
 ~  � generate /(Ljava/lang/Object;)Lkotori/gfx/KGLScriptModel;
 d � � setJudgmentKind
 f � 3 Lga2/stage/ItemModel; � JUDGE_DAMAGE_HIT	 � �  enableCollision (Z)V
 f � � 
setVisible
 f � � A(Ljava/lang/Object;Ljava/lang/String;)Lkotori/gfx/KGLScriptModel;
 d � �D efx/electric/rnd_thunder.scm�        �333?�  ?�  ?�               	main_core anime (F)V
 d � �A   
 H 0 3 setColor (FFFF)V
 d � �=��� 	fadeColor (FFFFI)V
 d � �>��� (FF)V
 d � �AP  =L��A�   
setMixture (Lkotori/data/MixtureData;)V
 d � � mixtureMotion (IFFF)V
 d � �Ap  A�   (IFFFFFFF)V
 d � �C�  @�  D *efx/stageitem/core/core_efx_center_mix.scm�        ��?�  ?�  ?�              A removeMixture
 d � 1 	playSound
 d � 3 vibe
 d � �=�\)CH   getParam &(Ljava/lang/String;)Ljava/lang/Object;
 f � � HP Ljava/lang/Number; � intValue
 � � 2 addHP
 f � 3 rndf (F)F
 H � � hpb (FFF)V
 ~ � �C�   scale
 ~ � � brown?L��<uA�  >�=q<���A�  D /efx/explode/exp_sphere1.scm�        �333?333?333?333             quake
 d � 3 f h g j g m l o l q l - 1  i� k�n�p�r��  s 1  +*� zW�*� }� �W*�� �W*� �� �W*� �W*� W�  t 1  �*� �W*��� �W*�� �W* � �W*���� �W*���,� �W*2� �W*��� �W*2� �W*��� �W*� � �W*�� �W*2� �W*��� �W* �� �W*�� �W*d� �W*d� �W*d� �W*�� �W*�� �W*,� �W*�� �W*� �W*d� �W*� �W�  u 1  *� �W*� �W*��,� �W�  v 1  *� �W*� �W*� W�  w 1  *� W*�� �L*+� ¶ �W�  x 1 J*� � �W*�� �W�˸ �˸ �˸ Ƕ �W��� �b� �W*�ζ �W*� �W*�� �W*��� �W*2� �W�˸ �˸ �˸ Ƕ �W��� �b� �W*�ζ �W*2� �W�˸ �˸ �˸ Ƕ �W��� �b� �W*�ζ �W*2� �W*� � �W*�� �W*��\�� �W*2� �W*�Ҷ �W*d� �W*��\�� �W* �� �W*��� �W*!� �W*ն �W*� �W*d� �W*� �W*�� �W*� �W� Щ#�� � =�<�x[�?f&�?Me�?[X� � coreType�?�  � HP�A�  �	 	collision�
1    =��;���?333?333?��