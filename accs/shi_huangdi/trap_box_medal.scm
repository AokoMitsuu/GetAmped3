	� G 1/efx/stageitem/trap/treasure_box/treasure_box.motv�       �V�jA��ŇX#¦�{h�Xs�6J[���I2d���Ll�܂RT��� /�kA|_b��1�gO94V����a��3��O}�;{S:hIcu�V���ٴ�sK&}�\�QN�u0�kG���8&B�7�;����g�1��%I���8Gvo�|?
ߎ�~y�.�{��óh��Qxo�����P�m��F����MAT�pK��4����z+�D�t[�*g�j�r���jA����"�Յ�0��؅�%��v�M�y��\���5�������Ա�������٢;z��Ԙ��)== �'��F�;�˺~�Ͼd�&%�.̓}Ęj-Yb�� ��\����تә�ԥ�j�����2K��A��:�4�v}�*�����K��[��bU��W7V���kk��ʊ����}f)�*�Nӈ��.�ݤ )�3���	kf�]][Y����t'\�.�{�r�<gh&8�!�1w����uy�t���WP��{��]��2�CYN�`��+B/���]�bK�;7R�[�,o�ʒ�bL���1O�dd�`P{�1�����m�*�#�[�#��R���#��z�uc��~�^�q�XI0��ܚ�s��K<-������ �q)M��8�4�2�VM��Z�o�i& �ص$�&�V^*��^���q\�y����9<ə�	�9ɛ�&�!�R5,rބ��E��	 ����*FI����W$��J�.U�F����(lЂ�
U�C�������(|�����VFO���k�x���׃�Q�
FY����{o_��h7g���C�aV'�8m���8��Q{�w�_v�ث��;�����;��=��6���\��<�g�q� ��m�   L  Lga2/stage/AmpedScript; e Lkotori/data/KGLGenerationData; glowD /efx/flash/glow01_yellow2.ptc�    ?       @@  @@  @@                ib_flashD */efx/stageitem/trap/item_box/ib_flash1.scm�            ?�  ?�  ?�                kirakiraD /efx/kirakira/03.ptc�    >���    ?�  ?�  ?�                i start destroy broken open
 H 0 3 
setVisible (Z)V
 f t u setEdge
 f w u setColor (FFFF)V
 d y z setRenderType
 d | 3 setBlend
 d ~ 3 	fadeColor (FFFFI)V
 d � � 	playSound
 d � 3
 H 0 1 (FFF)Lkotori/geom/Vector3D;
 H  � groundCheck (Lkotori/geom/Vector3D;)Z
 f � ����� setJudgmentKind
 f � 3 enableCollision
 f � u lockItemScript
 f � u lockTimeCount
 f � u clearJudgmentKind
 f � 1 kill
 f � 1 
stopScript
 f � 1 anime (F)V
 d � � g pos (FFF)V
 � � � hpb
 � � � scale
 � � � generate A(Ljava/lang/Object;Ljava/lang/String;)Lkotori/gfx/KGLScriptModel;
 d � �@@  >��� dropStageItem (IIII)V
 f � �?@   finishAllConnections
 d � 1 f h g j g l g n  - 1  i� k�m��  o 1  �*� sW*� vW*� xW*� {W*2� sW*� vW*� }W*� W* �� �W*d� sW*3� �W*d� sW*� xW*� W� *� �W*�� �� �К��*� �W*� �W�  p 1  #*� �W*� �W*� �W*� �W*� �W*� W�  q 1  N*� �W*� �W*� �W*� �W*� sW*� xW*� }W*� W*,� �W*,� sW*� �W*� �W�  r 1 �*� vW*� sW*� vW*� }W*� W*� {W*� �W*2� sW� b� �W� � �W� � �W*� o� �W� b� �W� � �W� �� �W*� o� �W��� �W�� �W�� �W*�o� �W�� E*d� �W�p� +*d� �W*d� �W*d� �W*d� �W�Y`�W�
���*�� sW� b� �W� � �W� � �W*� o� �W� b� �W� � �W� �� �W*� o� �W��� �W�� �W��� �W*�o� �W*�� sW� b� �W� � �W� � �W*� o� �W� b� �W� � �W� �� �W*� o� �W��� �W�� �W�� �W*�o� �W*�� sW� b� �W� � �W� � �W*� o� �W� b� �W� � �W� �� �W*� o� �W��� �W�� �W�� �W*�o� �W*�� sW� b� �W� � �W� � �W*� o� �W� b� �W� � �W� �� �W*� o� �W��� �W�� �W�� �W*�o� �W*�� �W*� W*� xW*�� sW*� vW*�� sW*� �W*� �W*� �W� �vy�    >��    >���>��>���� � 
HP_nojudge�@�  � collisiontype�    � 	collision�1    >aG�    >u>aG�>��