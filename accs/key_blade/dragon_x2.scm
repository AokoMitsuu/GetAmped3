	� G $/accs/dragon_master_black/dragon.skn ��       �V�n�F]Ө���Q�rhjF�����R��Va�EwĈ�S��=�B@���Ȧ@wA7)�n���K���nzgFi=Ȫ�	��{gν<��~��c�K�N,H�:��O�g^�����Ƃ>����G��R8����8kM����;�D�%��c�jF�P�x��_~��zy�������O����<�o7WOo���͋����5���4q�LB�&���%�c6����3`c�fndLV���5#�m
���/�??�w�;����!踖+�θ K-�e9�j�@*,*ˇS��з}�����d�˧��iD��h��Fc�R1��������yA�pb/�U%�"B��#wt�-.��$�(�iO':}��f����r*��{ħVF@nH=Q�?�+��XD\����G�~�_Ps���4�����M�g$t�b*N�u�_��eBV��a����I#_��YKzv��O����A���>�,l�+1�] �#ЎP��1�om|��!��������H/���� VQ�5�TZ�%��P�L�;��@�Q������z+�x� �Y��i4�Ј�7�B*V:�,5+E$T�l���߲���ɔ쏑��3S��K�S(�7�*n��6�XuM��� ��)��S�q[� �&�:��Z�w3R����y{�H�d��\�k�DV��1�H�y������A"$�,�����*QP���+ym�6��k��g%U��+�$�@���n�v�G�S����o��UB3ͥ��{�;��BNQf�iF%��NVX*���7dqQ�o�zF�x�8�2m�hp?iN�C���q[����;�	U�k�,S�)͸�̸*^]?������^�����^��\�����J�E!g���خت+�WM���mm#������墍� F�^��^�.��Kv1hY�ʽI� Bɿ�b/��^R�tťY3� ����U�����B Sek����{[�[qީ�ۣ��Ҧ�8-��hҙI�edutN��<#�Cs���'�'�%`CI=��<�[_e��~D��;k�ɐ�?L���N\`MF�2$�m2T�V�v	:�lWnKN���:�Vr������ޞ��^�Y7�}F����^}0�.��5ɧ�`��ɿ����   {  Lga2/stage/AmpedScript; e Lkotori/data/MixtureData; mix�� kotori.data.MixtureData� /efx/mix/bar2.agi���� Lkotori/data/KGLGenerationData; windD /efx/tornado/wind.scm�            ?�  ?�  ?�               glow_white2D /efx/flash/glow01_white2.ptc�        ?   @�  @�  @�                
kira_17_wbD /efx/kirakira/17_wb.ptc�        ?   @   @   @                 start timeout setRenderType
 d s 3  / setBlend
 d v 3 setColor (FFFF)V
 d x y setShadowState
 d { 3 roll (Lkotori/geom/Vector3D;I)V
 f } ~-        D�   anime (F)V
 d � �B�   	playSound
 d � 3 
setMixture (Lkotori/data/MixtureData;)V
 d � � Lkotori/scp/KotoriScript; � 
accumlimit
 � � 3 size
 f � �<��� j pos (FFF)V
 � � � rndc (F)F
 H � � hpb
 � � �A�  B�   scale
 � � �@    parts	 � �  connect	 � �  generate A(Ljava/lang/Object;Ljava/lang/String;)Lkotori/gfx/KGLScriptModel;
 d � � 	start_bluC4   /(Ljava/lang/Object;)Lkotori/gfx/KGLScriptModel;
 d � �
 H 0 3-             rotate (Lkotori/geom/Quaternion;)V
 f � ��� kotori.geom.Quaternion�;�.    ?�       (FF)V
 d � �C  >�� mixtureMotion 	(IFFFFF)V
 d � �C�  D /efx/flash/glow01_white2.ptc =��;L��    @   @   @               =����L��D /efx/fire/fire_ball.scm=L�ͼ��
    >���>���>���             move
 f � �<�/ (FFFZ)V
 d � �C  @�  -�          C  A   -            ���"�4���.?]�׿   C   vibe (FFFFI)V
 d � �>L��G�P  yquake
 d � 3 attack E(Lkotori/geom/Vector3D;Lga2/stage/atk/AttackParam;)Lga2/stage/Attack;
 f � �-        ?�  � d��D $/efx/fire/fire_ball_key_blade_x2.scm�            ?�  ?�  ?�  C4          � hit_fe( d� !  �  �hp'       ,�>�        -        ?�  � d��D $/efx/fire/fire_ball_key_blade_x2.scm�            ?�  ?�  ?�  C4          �( d� !  �'�h�'       ,�>�        -        ?�  � d��D $/efx/fire/fire_ball_key_blade_x2.scm�            ?�  ?�  ?�  C4          �( d� !  ����h�'       ,�>�        C   	fadeColor
 d � �D /efx/flash/glow01_white2.ptc     >L��    @   @   @                 kill
 f � 1 f h g k j m j o j - 1  i� l�n�p��  q 1  �*u� tW*� wW*� zW*� |W*�� W*�� �W*� �W*� � �W<� �* ȶ �W*��j� �W�� �W��� ���� �� �W��� �W�� ��� �*��� �W
p� &��� ��� ��� �� �W*�� �W*�� �W*
� �W�W(��|�  r 1 �*� �W*u� tW*� wW*� zW*�� W*�� �W*��� �W*�� �W*�� �W<� D���� �W��� ��� ��� �� �W�� �W�� ��� �*��� �W�W
���*�� �W<� *�� �W*
� �W�W���*���� �W* ȶ �W*�� W*���� �W*d� �W*�� W*Ƕ �W*���� �W*��,� �W*� �W* �� �W*�Զ �W*�ֶ �W*�ض �W*2� �W*��� �W* ȶ �W*� tW*� wW*,� �W*� |W*ܶ �W*,� �W*� �W� ef�    ���j<���?|�C>a�w?/���  